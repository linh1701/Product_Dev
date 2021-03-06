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
 * 1. ????ng nh???p
 *  get ch???y ra login
 * post: th???c hi???n login
 * 
 * 2. http://locallhost:3000/dangxuat
 * get: chay ????ng xu???t
 * 
 * 3.S???n ph???m
 * http://locallhost:3000/san-pham
 * get: xu???t danh s??ch s???n ph???m
 * 
 * 4
 *  chi ti???t 1 s???n ph???m
 * http://localhost:/san-pham/:id/edit
 * get l???y th??ng tin chi ti???t 1 s???n ph???m
 * put: c???p nh???y th??ng tin s???n ph???m
 * 
 * 5. X??a s???n ph???m
 * http://localhost:/san-pham/:id/delete
 *  delete: x??a 1 s???n ph???m
 * 
 * 6. th???ng k??
 * http://localhost:/san-pham/thong-ke
 * get: l???y th???ng s???n ph???m
 */