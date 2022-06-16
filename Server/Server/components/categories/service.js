const async = require("hbs/lib/async")

const categoryModel = require('./model');
/**
 * lay danh sach cac loai danh muc
 * @returns 
 */
exports.getCategories = async () => {
    //return data;
    const categories = await categoryModel.find();
    return categories;
}


/**
 * 
 * @param {ma danh muc} id 
 * @returns danh muc
 */
exports.getCategoryById = async (id) => {
    //const category = data.filter(item => item._id == id)[0];
   // return category;

   const category = await categoryModel.findById(id);
  return category;
}

exports.insert = async (category) => {
  // data.push(product);
  const p = new categoryModel(category);
  await p.save();
};

exports.delete = async (id) => {
  //data = data.filter(item => item._id != id);
  await categoryModel.findByIdAndDelete(id);
};

exports.update = async (id, category) => {
  await categoryModel.findByIdAndUpdate(id,category);
};

var data = 
[{
  "_id": 1,
  "name": "Wine - Magnotta - Pinot Gris Sr",
  "description": "Aenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.\n\nQuisque id justo sit amet sapien dignissim vestibulum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est. Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros.\n\nVestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat."
}, {
  "_id": 2,
  "name": "Sprite - 355 Ml",
  "description": "Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.\n\nPellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus."
}, {
  "_id": 3,
  "name": "Broom And Brush Rack Black",
  "description": "Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat.\n\nCurabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.\n\nInteger tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat."
}, {
  "_id": 4,
  "name": "Eel - Smoked",
  "description": "Quisque id justo sit amet sapien dignissim vestibulum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est. Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros.\n\nVestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat."
}, {
  "_id": 5,
  "name": "Potatoes - Yukon Gold 5 Oz",
  "description": "Duis consequat dui nec nisi volutpat eleifend. Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus.\n\nMauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis. Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci. Mauris lacinia sapien quis libero.\n\nNullam sit amet turpis elementum ligula vehicula consequat. Morbi a ipsum. Integer a nibh."
}]