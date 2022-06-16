/**
 * sevicc: tầng giao tiếp database
 */

const productModel = require("./model");

const async = require("hbs/lib/async");

/**
 * Lấy danh sách sản phẩm
 */

exports.getProducts = async () => {
  const products = await productModel.find().populate("category_id");
  return products;
};

//exports.getProducts = async () =>{
//   return data;
//}

exports.getProductById = async (id) => {
  //const product = data.filter(item => item._id == id)[0];
  // return product;

  const product = await productModel.findById(id).populate("category_id");
  return product;
};

exports.insert = async (product) => {
  // data.push(product);
  const p = new productModel(product);
  await p.save();
};

exports.delete = async (id) => {
  //data = data.filter(item => item._id != id);
  await productModel.findByIdAndDelete(id);
};

exports.update = async (id, product) => {
  //data = data.map((item) => {
    //if (item._id == id) {
   //   item = { ...item, ...product };
   // }
   // return item;
 // });

  await productModel.findByIdAndUpdate(id,product);
};
var data = [
  {
    _id: 31,
    name: "Trueblue - Blueberry",
    price: 96,
    quantiny: 83,
    date: "2021-04-01",
    image: "https://luv.vn/wp-content/uploads/2021/08/hinh-anh-gai-xinh-9.jpg",
    description:
      "Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum. Aliquam non mauris.",
    catagory_id: {
      _id: 3,
      name: "Broom And Brush Rack Black",
      description:
        "Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat.\n\nCurabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.\n\nInteger tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.",
    },
  },
  {
    _id: 46,
    name: "Cheese - Cheddar, Mild",
    price: 53,
    quantiny: 82,
    date: "2021-07-25",
    image: "https://luv.vn/wp-content/uploads/2021/08/hinh-anh-gai-xinh-9.jpg",
    description:
      "Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla. Sed vel enim sit amet nunc viverra dapibus. Nulla suscipit ligula in lacus.\n\nCurabitur at ipsum ac tellus semper interdum. Mauris ullamcorper purus sit amet nulla. Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam.",
    catagory_id: {
      _id: 3,
      name: "Broom And Brush Rack Black",
      description:
        "Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat.\n\nCurabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.\n\nInteger tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.",
    },
  },
  {
    _id: 68,
    name: "Wine - Two Oceans Cabernet",
    price: 56,
    quantiny: 2,
    date: "2022-01-08",
    image: "https://luv.vn/wp-content/uploads/2021/08/hinh-anh-gai-xinh-9.jpg",
    description:
      "Vestibulum quam sapien, varius ut, blandit non, interdum in, ante. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio. Curabitur convallis.\n\nDuis consequat dui nec nisi volutpat eleifend. Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus.",
    catagory_id: {
      _id: 3,
      name: "Broom And Brush Rack Black",
      description:
        "Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat.\n\nCurabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.\n\nInteger tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.",
    },
  },
  {
    _id: 10,
    name: "Nut - Cashews, Whole, Raw",
    price: 89,
    quantiny: 68,
    date: "2021-04-01",
    image: "https://luv.vn/wp-content/uploads/2021/08/hinh-anh-gai-xinh-9.jpg",
    description:
      "Quisque id justo sit amet sapien dignissim vestibulum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est. Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros.",
    catagory_id: {
      _id: 2,
      name: "Sprite - 355 Ml",
      description:
        "Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.\n\nPellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus.",
    },
  },
  {
    _id: 3,
    name: "Rice Wine - Aji Mirin",
    price: 100,
    quantiny: 93,
    date: "2021-04-01",
    image: "https://luv.vn/wp-content/uploads/2021/08/hinh-anh-gai-xinh-9.jpg",
    description:
      "Morbi non lectus. Aliquam sit amet diam in magna bibendum imperdiet. Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis.",
    catagory_id: {
      _id: 2,
      name: "Sprite - 355 Ml",
      description:
        "Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.\n\nPellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus.",
    },
  },
  {
    _id: 21,
    name: "Rabbit - Whole",
    price: 66,
    quantiny: 87,
    date: "2021-04-01",
    image: "https://luv.vn/wp-content/uploads/2021/08/hinh-anh-gai-xinh-9.jpg",
    description:
      "Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum.\n\nCurabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est.",
    catagory_id: {
      _id: 2,
      name: "Sprite - 355 Ml",
      description:
        "Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.\n\nPellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus.",
    },
  },
  {
    _id: 86,
    name: "Petit Baguette",
    price: 97,
    quantiny: 24,
    date: "2021-04-01",
    image: "https://luv.vn/wp-content/uploads/2021/08/hinh-anh-gai-xinh-9.jpg",
    description:
      "Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus.",
    catagory_id: {
      _id: 2,
      name: "Sprite - 355 Ml",
      description:
        "Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.\n\nPellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus.",
    },
  },
  {
    _id: 12,
    name: "Beer - Molson Excel",
    price: 50,
    quantiny: 72,
    date: "2021-04-01",
    image: "https://luv.vn/wp-content/uploads/2021/08/hinh-anh-gai-xinh-9.jpg",
    description:
      "Morbi porttitor lorem id ligula. Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem.\n\nFusce consequat. Nulla nisl. Nunc nisl.",
    catagory_id: {
      _id: 2,
      name: "Sprite - 355 Ml",
      description:
        "Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.\n\nPellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus.",
    },
  },
  {
    _id: 77,
    name: "Oil - Coconut",
    price: 60,
    quantiny: 10,
    date: "2021-04-01",
    image: "https://luv.vn/wp-content/uploads/2021/08/hinh-anh-gai-xinh-9.jpg",
    description:
      "Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est.\n\nPhasellus sit amet erat. Nulla tempus. Vivamus in felis eu sapien cursus vestibulum.",
    catagory_id: {
      _id: 2,
      name: "Sprite - 355 Ml",
      description:
        "Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.\n\nPellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus.",
    },
  },
  {
    _id: 40,
    name: "Sour Cream",
    price: 62,
    quantiny: 18,
    date: "2021-04-01",
    image: "https://luv.vn/wp-content/uploads/2021/08/hinh-anh-gai-xinh-9.jpg",
    description:
      "Nullam sit amet turpis elementum ligula vehicula consequat. Morbi a ipsum. Integer a nibh.\n\nIn quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet.",
    catagory_id: {
      _id: 2,
      name: "Sprite - 355 Ml",
      description:
        "Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.\n\nPellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus.",
    },
  },
  {
    _id: 61,
    name: "Chevere Logs",
    price: 72,
    quantiny: 50,
    date: "2021-04-01",
    image: "https://luv.vn/wp-content/uploads/2021/08/hinh-anh-gai-xinh-9.jpg",
    description:
      "Morbi non lectus. Aliquam sit amet diam in magna bibendum imperdiet. Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis.\n\nFusce posuere felis sed lacus. Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl. Nunc rhoncus dui vel sem.",
    catagory_id: {
      _id: 2,
      name: "Sprite - 355 Ml",
      description:
        "Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.\n\nPellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus.",
    },
  },
  {
    _id: 10,
    name: "Octopus",
    price: 69,
    quantiny: 100,
    date: "2021-04-01",
    image: "https://luv.vn/wp-content/uploads/2021/08/hinh-anh-gai-xinh-9.jpg",
    description:
      "Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum.",
    catagory_id: {
      _id: 2,
      name: "Sprite - 355 Ml",
      description:
        "Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.\n\nPellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus.",
    },
  },
  {
    _id: 6,
    name: "Raisin - Golden",
    price: 66,
    date: "2021-04-01",
    image: "https://luv.vn/wp-content/uploads/2021/08/hinh-anh-gai-xinh-9.jpg",
    description:
      "Quisque id justo sit amet sapien dignissim vestibulum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est. Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros.\n\nVestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat.",
    catagory_id: {
      _id: 2,
      name: "Sprite - 355 Ml",
      description:
        "Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.\n\nPellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus.",
    },
  },
  {
    _id: 40,
    name: "Red Currants",
    price: 88,
    quantiny: 97,
    date: "2021-04-01",
    image: "https://luv.vn/wp-content/uploads/2021/08/hinh-anh-gai-xinh-9.jpg",
    description:
      "Curabitur at ipsum ac tellus semper interdum. Mauris ullamcorper purus sit amet nulla. Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam.",
    catagory_id: {
      _id: 2,
      name: "Sprite - 355 Ml",
      description:
        "Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.\n\nPellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus.",
    },
  },
  {
    _id: 10,
    name: "Pie Filling - Pumpkin",
    price: 60,
    quantiny: 37,
    date: "2021-04-01",
    image: "https://luv.vn/wp-content/uploads/2021/08/hinh-anh-gai-xinh-9.jpg",
    description:
      "Morbi porttitor lorem id ligula. Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem.",
    catagory_id: {
      _id: 2,
      name: "Sprite - 355 Ml",
      description:
        "Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.\n\nPellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus.",
    },
  },
  {
    _id: 29,
    name: "Barramundi",
    price: 58,
    quantiny: 29,
    date: "2021-04-01",
    image: "https://luv.vn/wp-content/uploads/2021/08/hinh-anh-gai-xinh-9.jpg",
    description:
      "Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.\n\nPraesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.",
    catagory_id: {
      _id: 3,
      name: "Broom And Brush Rack Black",
      description:
        "Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat.\n\nCurabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.\n\nInteger tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.",
    },
  },
  {
    _id: 16,
    name: "Muffin - Banana Nut Individual",
    price: 88,
    quantiny: 86,
    date: "2021-04-01",
    image: "https://luv.vn/wp-content/uploads/2021/08/hinh-anh-gai-xinh-9.jpg",
    description: "Sed ante. Vivamus tortor. Duis mattis egestas metus.",
    catagory_id: {
      _id: 2,
      name: "Broom And Brush Rack Black",
      description:
        "Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat.\n\nCurabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.\n\nInteger tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.",
    },
  },
  {
    _id: 38,
    name: "Chinese Foods - Chicken Wing",
    price: 52,
    quantiny: 63,
    date: "2021-04-01",
    image: "https://luv.vn/wp-content/uploads/2021/08/hinh-anh-gai-xinh-9.jpg",
    description:
      "Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.\n\nInteger tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.",
    catagory_id: {
      _id: 2,
      name: "Broom And Brush Rack Black",
      description:
        "Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat.\n\nCurabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.\n\nInteger tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.",
    },
  },
  {
    _id: 47,
    name: "Wine - Ice Wine",
    price: 79,
    quantiny: 28,
    date: "2021-04-01",
    image: "https://luv.vn/wp-content/uploads/2021/08/hinh-anh-gai-xinh-9.jpg",
    description:
      "Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.",
    catagory_id: {
      _id: 4,
      name: "Broom And Brush Rack Black",
      description:
        "Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat.\n\nCurabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.\n\nInteger tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.",
    },
  },
  {
    _id: 24,
    name: "Basil - Fresh",
    price: 66,
    quantiny: 87,
    date: "2021-04-01",
    image: "https://luv.vn/wp-content/uploads/2021/08/hinh-anh-gai-xinh-9.jpg",
    description:
      "Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui.",
    catagory_id: {
      _id: 2,
      name: "Sprite - 355 Ml",
      description:
        "Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.\n\nPellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus.",
    },
  },
];
