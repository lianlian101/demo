/**
 * 格式校验
 */
 function checkEmail(email){
    return /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/.test(email);
}
 
function checkPhone(phone){
    return /^1(3|4|5|7|8)\d{9}$/.test(phone);
}

function checkCode(code){
    return /^\d{6}$/.test(code);
}