var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

// // post thêm product
// router.post(
//   "/them-moi",
//   async function (req, res, next) {
//     // thêm mới sản phẩm vào database
//     let { body, file } = req;
//     const result = await productController.insert(body);
//     body = { ...body};
//     console.log(">>>>>>>>>aaaa", body);
//     if (result) {
//       res.json({ status: true});
//     }
//     else {
//       res.json({ status: false});
//     }
//   }
// );

module.exports = router;
