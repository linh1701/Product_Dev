var express = require('express');
var router = express.Router();

const productController = require('../components/products/controller');
const categoryController = require('../components/categories/controller');
const authentication = require('../middle/authentication');
/**
 *  http://localhost:3000/danh-muc/
 * method: get
 * deltail: lấy danh sách danh muc
 * author: linh đinh
 * date: 06/04/2022
 */
router.get('/',[authentication.checkLogin], async function(req, res, next) {
    // lấy danh sách sản phẩm bằng database
    const data = await categoryController.getCategories();
   res.render('category', {category: data});
  });
  
  /**
 * http://localhost:/danh-muc/:id/edit
 * method: get
 * deltail: lấy thông tin chi tiết 1 danh mục
 * author: linh đinh
 * date: 17/03/2022
 */
 router.get('/:id/edit',[authentication.checkLogin],async function(req, res, next) {
    // lấy thông tin chi tiết danh mục
    const {id} = req.params;
    const category = await categoryController.getCategoryById(id);
    // console.log(product);
    res.render('editCategory', {category: category});
  });
  
  /**
   *  http://localhost:3000/san-pham/
   * method: put
   * deltail: cập nhật thông tin 1 danh mục
   * author: linh đinh
   * date: 17/03/2022
   */
  
   router.put('/', function(req, res, next) {
    // cập nhật thông tin 1 sản phẩm
   res.render('category', {});
  });
  
  /**
   *  http://localhost:3000/san-pham/
   * method: post
   * deltail: cập nhật thông tin 1 danh mục
   * author: linh đinh
   * date: 17/03/2022
   */
   router.post('/:id/edit',[authentication.checkLogin],async function(req, res, next) {
    // cập nhật thông tin 1 sản phẩm
    let {body, file, params} = req;
    await categoryController.update(params.id, body);
    res.redirect('/danh-muc');
  });
  
  
  
  /**
   *  http://localhost:3000/san-pham/
   * method: get
   * deltail: thêm mới san pham
   * author: linh đinh
   * date: 22/3/2022
   */
  router.get('/them-moi',[authentication.checkLogin],async function(req, res, next) {
    // thêm sản phẩm bằng database
   res.render('addCategory', {});
  });
  
  /**
   *  http://localhost:3000/san-pham/
   * method: post
   * deltail: thêm mới san pham
   * author: linh đinh
   * date: 22/3/2022
   */
  
  //middleware
   router.post('/',[authentication.checkLogin],async function(req, res, next) {
    // thêm mới sản phẩm vào database
    let {body, file} = req;
//    console.log(">>>>>>>>>>>>"+ body);
    await categoryController.insert(body);
   res.redirect('/danh-muc');
  });
  
  /**
   * http://localhost:/san-pham/:id/delete
   * method: delete
   * deltail: xóa 1 sản phẩm khỏi database
   * author: linh đinh
   * date: 17/03/2022
   */
  router.delete('/:id/delete',[authentication.checkLogin],async function(req, res, next) {
    // cập nhật thông tin 1 sản phẩm
    const {id} = req.params;
    await categoryController.delete(id);
    res.json({result: true})
  });

module.exports = router;