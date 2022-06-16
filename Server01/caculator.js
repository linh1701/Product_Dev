exports.tinhTong = 
function (a,b){
    return a + b;
};

exports.tinhHieu = (a,b) => a - b;
exports.giaiPhuongTrinh = (a,b,c) => 
  d = (b * b) - (4 * a * c)
        if(a == 0) {
            if(b == 0){
                if (c == 0) {
                    document.write('All are solutions of x')
                  
                }
                else{
                    document.write(`Can't solve for x`)
                }
            }
            else{
                document.write('x = '+(-c/b))
            }
        }
    else if (d >= 0) {
            document.write(`x1 = `+ ((-b + Math.sqrt(d)) / (2 * a)))
            document.write(`<br>`)
            document.write(`x2 = `+ ((-b - Math.sqrt(d)) / (2 * a)))
        } else {
            document.write(`Can't solve for x`)
        }