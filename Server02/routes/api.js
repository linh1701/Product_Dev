var express = require("express");
var router = express.Router();

const jwt = require("jsonwebtoken");
const productController = require('../components/products/controller');

//http://localhost:3000/api/san-pham
// thêm midded
router.get('/product', async function(req, res, next) {
  // lấy danh sách sản phẩm bằng database
  const product = await productController.getProducts();
  res.json(product);

   console.log(">>>>>>>>>", product);
});

router.get('/product/:id/detail', async function(req, res, next) {
  // lấy danh sách sản phẩm bằng database
  const {id} = req.params;
  const product = await productController.getProductById(id);
  res.json(product);
});



module.exports = router;
