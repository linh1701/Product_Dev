var express = require("express");
var router = express.Router();

const productController = require("../components/products/controller");
const categoryController = require("../components/categories/controller");
const upload = require("../middle/upload");
const async = require("hbs/lib/async");

const authentication = require("../middle/authentication");
/**
 *  http://localhost:3000/san-pham/
 * method: get
 * deltail: lấy danh sách sản phẩm
 * author: linh đinh
 * date: 17/03/2022
 */
router.get("/", [authentication.checkLogin], async function (req, res, next) {
  // lấy danh sách sản phẩm bằng database
  const data = await productController.getProducts();
  res.render("product", { product: data });
});

/**
 * http://localhost:/san-pham/:id/edit
 * method: get
 * deltail: lấy thông tin chi tiết 1 sản phẩm
 * author: linh đinh
 * date: 17/03/2022
 */
router.get("/:id/edit",[authentication.checkLogin],async function (req, res, next) {
    // lấy thông tin chi tiết làm sản phẩm
    //Lay thong tin chi tiet san pham
    const { id } = req.params;
    const product = await productController.getProductById(id);
    const categories = await categoryController.getCategoriesForOneProduct(
      product.category_id._id
    );
    res.render("editProduct", { product: product, categories: categories });
  }
);

/**
 *  http://localhost:3000/san-pham/
 * method: put
 * deltail: cập nhật thông tin 1 sản phẩm
 * author: linh đinh
 * date: 17/03/2022
 */

router.put("/", function (req, res, next) {
  // cập nhật thông tin 1 sản phẩm
  res.render("product", {});
});

/**
 *  http://localhost:3000/san-pham/
 * method: post
 * deltail: cập nhật thông tin 1 sản phẩm
 * author: linh đinh
 * date: 17/03/2022
 */
 router.post('/:id/edit', [upload.single('image')], async function (req, res, next) {
  let { body, file, params } = req;
  delete body.image;
  if (file) {
      let image = `http://10.82.151.64:3000/images/${file.filename}`;
      body = { ...body, image: image }
  }
  await productController.update(params.id, body);
  res.redirect('/san-pham');
});
/**
 *  http://localhost:3000/san-pham/
 * method: get
 * deltail: thêm mới san pham
 * author: linh đinh
 * date: 22/3/2022
 */
router.get(
  "/them-moi",
  [authentication.checkLogin],
  async function (req, res, next) {
    // thêm sản phẩm bằng database
    const categories = await categoryController.getCategories();
    res.render("addProduct", { categories: categories });
  }
);

/**
 *  http://localhost:3000/san-pham/
 * method: post
 * deltail: thêm mới san pham
 * author: linh đinh
 * date: 22/3/2022
 */

//middleware
router.post(
  "/",
  [authentication.checkLogin],
  [upload.single("image")],
  async function (req, res, next) {
    // thêm mới sản phẩm vào database
    let { body, file } = req;
    let image = "";
    if (file) {
      image = `http://10.82.151.64:3000/images/${file.filename}`;
    }
    body = { ...body, image: image };
    console.log(">>>>>>>>>", body);
    await productController.insert(body);
    res.redirect("/san-pham");
  }
);

/**
 * http://localhost:/san-pham/:id/delete
 * method: delete
 * deltail: xóa 1 danh mục khỏi database
 * author: linh đinh
 * date: 17/03/2022
 */
router.delete(
  "/:id/delete",
  [authentication.checkLogin],
  async function (req, res, next) {
    // cập nhật thông tin 1 sản phẩm
    const { id } = req.params;
    await productController.delete(id);
    res.json({ result: true });
  }
);

module.exports = router;
