const { query } = require('express');
var express = require('express');
var router = express.Router();
const userController = require('../components/users/controller');
const jwt = require('jsonwebtoken')

const authentication = require('../middle/authentication');

/* GET home page. */

router.get('/',[authentication.checkLogin], function(req, res, next) {
  res.render('index', {});
});


/**
 *  http://localhost:3000-dang-nhap
 * method: get
 * deltail: hien thi trang login
 * author: linh đinh
 * date: 17/03/2022
 */
router.get('/dang-nhap',[authentication.checkLogin], function(req, res, next) {

  res.render('login', {});
});

router.get('/register', function(req, res, next) {
  res.render('register', {});
});





// Gửi thông tin từ client len server
/**
 *  http://localhost:3000-dang-nhap
 * method: post
 * deltail: thực hiện trang login
 * author: linh đinh
 * date: 17/03/2022
 */

router.post('/login', async function(req, res, next) {
  const {email, password} = req.body;
   const result = await userController.login(email,password)
// neu dang nhap thanh cong
  if(result){
    // Nếu đúng kiểm tra sản phẩm
    const token = jwt.sign({id: result._id, username: result.username}, 'iloveyou')
    req.session.token = token;
    res.redirect('/san-pham');
  }
// nếu sai quay lại đăng nhập
  else{
    res.redirect('/dang-nhap');
  }
});

/**
 *  http://localhost:3000-dang-xuat
 * method: get
 * deltail: thực hiện đang xuất
 * author: linh đinh
 * date: 17/03/2022
 */
 router.get('/dang-xuat',[authentication.checkLogin], function(req, res, next) {
  req.session.destroy(function(err){
    res.redirect('dang-nhap');
  })
});


// req.body: submit from
// req.query: ?a=10&b=20
// que.params: /:id/sumbit
module.exports = router;


