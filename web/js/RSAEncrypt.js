/**
 * Created by 李佳骏 on 2017/4/9.
 */
var encrypt = new JSEncrypt();
var RSA_MAX_SIZE = parseInt("100");
function initRSAEncrypt(publicKey) {
    encrypt.setPublicKey(publicKey);
}
function getParts(data){
    var blockSize = parseInt(data.length / RSA_MAX_SIZE);
    blockSize = data.length % RSA_MAX_SIZE == 0 ? blockSize : blockSize + 1;
    var parts = new Array(blockSize);
    for (var i = 0; i < blockSize; i++) {
        var endIndex;
        if (i == (blockSize - 1))
            endIndex = data.length;
        else
            endIndex = (i + 1) * RSA_MAX_SIZE;
        parts[i] = data.substring(i * RSA_MAX_SIZE, endIndex);
    }
    return parts;
}
function encodePart(data) {
    return encrypt.encrypt(data);
}
function encodeData(data) {
    if(encrypt==null)
        return null;
    var parts = getParts(data);
    for(var i=0;i<parts.length;i++){
        var temp = encrypt.encrypt(parts[i]);
        parts[i] = temp;
    }
    return parts;
}
