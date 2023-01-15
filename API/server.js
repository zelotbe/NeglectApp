require('dotenv').config();
// Require express
const express = require("express");
const req = require('express/lib/request');
const fs = require('fs');
const multer = require('multer');
const csv = require('csv-parser');
const upload = multer({ dest: 'uploads/' });
// Initialize express
const app = express();
const PORT = 3000;
const DB_HOST = process.env.DB_HOST;
const DB_DATABASE = process.env.DB_DATABASE;
const DB_USERNAME = process.env.DB_USERNAME;
const DB_PASSWORD = process.env.DB_PASSWORD;
const mysql = require('mysql');
// database connection
const db = mysql.createConnection({
    user: DB_USERNAME,
    password: DB_PASSWORD,
    host: DB_HOST,
    database: DB_DATABASE,
    ssl: { ca: fs.readFileSync("DigiCertGlobalRootCA.crt.pem") }
});
// parse JSON
app.use(express.json());

// parse URL encoded data
app.use(express.urlencoded({ extended: true }));
// create a server
app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});

app.get("/sessions", (req, res) => {
    db.query("SELECT * from data", (error, data) => {
        if (error) {
            return res.json({ status: "ERROR", error });
        }

        return res.json(data);
    });
})

app.post('/session', upload.single('file'), (req, res) => {
    // read csv file and push data if it doesn't exist yet
    fs.createReadStream(req.file.path)
        .pipe(csv())
        .on('data', (data) => {
            // removing milliseconds from the date because MySQL rounds it up, 
            // therefore I can't use the date as a unique "id"
            var date = new Date(data.Datum);
            date.setMilliseconds(0)
            console.log(date.toISOString().slice(0, 19).replace('T', ' '))
            db.query(`SELECT * FROM sessions WHERE Datum = "${date.toISOString().slice(0, 19).replace('T', ' ')}"`,
                function (err, rows) {
                    if (err) throw err;
                    if (rows.length === 0) {
                        if (data.Interactie === 'true' || data.Interactie === 'false') {
                            data.Interactie = Boolean(data.Interactie);
                        }
                        if (data.Datum) {
                            data.Datum = date.toISOString().slice(0, 19).replace('T', ' ')
                        }
                        db.query('insert into sessions SET ?', data, function (err) {
                            if (err) throw err;
                        });
                    } else {
                        console.log(`Data already exists with Datum ${data.Datum}`)
                    }
                }
            )
            console.log(data);

        })
        .on('end', () => {
            console.log('IMPORTED DATA');
            fs.unlink(req.file.path, (err) => {
                if (err) {
                    console.error(err)
                    return
                }
                console.log("Removed file from uploads!")
            });
        });

    res.status(200).send('File uploaded');
})


