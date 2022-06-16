const categoryController = require('./service')
const categorytService= require('./service');
exports.getCategories = async () => {
    //return await categorytService.getCategories();
    let data = await categorytService.getCategories();
    data = data.map((item, index) => {
        item = {
            _id: item._id,
            name: item.name,
            description: item.description,
            index: index +1
        }
        return item;
    })
    return data;
}

exports.getCategoryById = async (id) => {
    //return await categoryController.getCategoryById(id);

    let category = await categorytService.getCategoryById(id);
    // console.log(product);
    category = {
        _id: category._id,
        name: category.name,
        description: category.description,
    }
    return category;
}

exports.getCategoriesForOneProduct = async (selectedId) => {
    let categories = await categoryController.getCategories();
    categories = categories.map(item => {
        // if (item._id == selectedId) {
        //     item = { ...item, selected: true }
        // } else {
        //     item = { ...item, selected: false }
        // }
        // return item
        item = {
            _id: item._id,
            name: item.name,
            description: item.description,
            selected: item._id.toString() == selectedId.toString()
        }
        return item;
    })
    return categories;
}

exports.insert = async (body) =>{
    await categorytService.insert(body);
}

exports.delete = async (id) =>{
    await categorytService.delete(id);
}

exports.update = async (id,category) =>{
    await categorytService.update(id,category);
}
  
