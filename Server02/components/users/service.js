// tầng giao tiếp với database
const userModel = require("./model");

exports.login = async (email) => {
  //const user = data.filter(item => item.email == email)[0];
  //return user;

  //select id, email, password from users where email = ' '

  const user = await userModel.findOne({ email: email }, "id emaul password");
  return user;
};

exports.register = async (email, password) => {
  const user = new userModel({ email, password });
  return await user.save();
};

// giai lap
var data = [{ id: 1, email: "admin@gmail.com", password: "123", name: "Linh" }];

var data = [
  { id: 1, email: "admin@gmail.com", password: "123", name: "Đinh Nhựt Linh" },
];
