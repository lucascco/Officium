/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var atribuirMascaraData = function(idCampo){
    $(idCampo).inputmask('99/99/9999', {"clearIncomplete": true});
};
var atribuirMascaraDataHora = function(idCampo){
    $(idCampo).inputmask('99/99/9999 99:99:99', {"clearIncomplete": true});
};
