const express = require('express');
const app = express();

app.get('/product/:id/price', (req, res) => {
    res.json({ price: 49.99 }); // Zwracamy cenę produktu
});

app.listen(8080, () => {
    console.log('Mock ProductService running on port 8081');
});
