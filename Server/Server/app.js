var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');

const cors = require('cors');
var indexRouter = require('./routes/index');
var productRouter = require('./routes/product');
var categoryRouter = require('./routes/category');
var apisRouter = require('./routes/api');

const mongoose = require('mongoose');
const session = require('express-session')
require('./components/users/model');
require('./components/categories/model');
require('./components/products/model');


var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'hbs');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use(session({
  secret: 'iloveyou',
  resave: true,
  saveUninitialized: true,
  cookie: { secure: false }
}));

app.all('/', function (request, response, next) {
  response.header("Access-Control-Allow-Origin", "*");
  response.header("Access-Control-Allow-Headers", "X-Requested-With");
  next();
});

mongoose.connect('mongodb+srv://linh1701:0365553814@cluster0.5op6e.mongodb.net/Demo1?retryWrites=true&w=majority', {  
  useNewUrlParser: true,
  useUnifiedTopology: true
})
.then(() => console.log('>>>>>>>>>> DB Connected!!!!!!'))
.catch(err => console.log('>>>>>>>>> DB Error: ', err));

app.use('/', indexRouter);
app.use('/san-pham', productRouter);
app.use('/danh-muc', categoryRouter);
app.use('/api', apisRouter);

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;

/**
 * 1. Đăng nhập
 *  get chạy ra login
 * post: thực hiện login
 * 
 * 2. http://locallhost:3000/dangxuat
 * get: chay đăng xuất
 * 
 * 3.Sản phẩm
 * http://locallhost:3000/san-pham
 * get: xuất danh sách sản phẩm
 * 
 * 4
 *  chi tiết 1 sản phẩm
 * http://localhost:/san-pham/:id/edit
 * get lấy thông tin chi tiết 1 sản phẩm
 * put: cập nhậy thông tin sản phẩm
 * 
 * 5. Xóa sản phẩm
 * http://localhost:/san-pham/:id/delete
 *  delete: xóa 1 sản phẩm
 * 
 * 6. thống kê
 * http://localhost:/san-pham/thong-ke
 * get: lấy thống sản phẩm
 */