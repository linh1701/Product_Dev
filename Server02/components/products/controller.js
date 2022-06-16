
const async = require('hbs/lib/async');
const productService = require('./service');
const date = require('../../until/date')


exports.getProducts = async () =>{

    let data = await productService.getProducts();
    data = data.map((item, index) => {
        item = {
            _id: item._id,
            name: item.name,
            price: item.price
        }
        return item;
    })
    return data;
}


exports.getProductById = async (id) => {

    let product = await productService.getProductById(id);
    // console.log(product);
    product = {
        released: date.format(product.released),
        _id: product._id,
        name: product.name,
        price: product.price
    }
    return product;
}

