var express = require("express");
var router = express.Router();

const jwt = require("jsonwebtoken");
const userController = require('../components/users/controller')
const productController = require('../components/products/controller');
const authentication = require('../middle/authentication');
//http://localhost:3000/api/login

router.post("/login", async function (req, res, next) {
  const { email, password } = req.body;
  const result = await userController.login(email, password);
  // neu dang nhap thanh cong
  if (result) {
    // Nếu đúng kiểm tra sản phẩm
    const token = jwt.sign({ id: result._id, username: result.username },"iloveyou");
    res.json({ status: true, result, token});
  }
  // nếu sai quay lại đăng nhập
  else {
    res.json({ status: false});
  }
});

//http://localhost:3000/api/register
router.post("/register", async function (req, res, next) {
  const { email, password, confirm_password} = req.body;
  const result = await userController.register(email, password, confirm_password);
  if (result) {
    const token = jwt.sign({ id: result._id, username: result.username },"iloveyou");
    res.json({ status: true});
  }
  else {
    res.json({ status: false});
  }
});

//http://localhost:3000/api/san-pham
// thêm midded
router.get('/product',[authentication.checkToken], async function(req, res, next) {
  // lấy danh sách sản phẩm bằng database
  const product = await productController.getProducts();
  res.json(product);

   console.log(">>>>>>>>>", product);
});

router.get('/product/:id/detail',[authentication.checkToken], async function(req, res, next) {
  // lấy danh sách sản phẩm bằng database
  const {id} = req.params;
  const product = await productController.getProductById(id);
  res.json(product);
});



module.exports = router;
