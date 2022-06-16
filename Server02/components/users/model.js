const mongoose = require('mongoose');
const Schema = mongoose.Schema;
const ObjectId = Schema.ObjectId;

const userSchema = new Schema({
    id: { type: ObjectId }, // khoa chinh
    email: { type: String, required: true },
    password: { type: String }
});

module.exports = mongoose.model('user', userSchema);


//mongodb+srv://linh1701:<password>@cluster0.5op6e.mongodb.net/myFirstDatabase?retryWrites=true&w=majority
//mongodb+srv://linh1701:<password>@cluster0.sozbn.mongodb.net/myFirstDatabase?retryWrites=true&w=majority