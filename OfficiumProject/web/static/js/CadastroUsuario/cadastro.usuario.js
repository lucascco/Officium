/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    atribuirMascaraData('#ipt_nascimento_cad');
    $('.senha').each(function(){
       $(this).attr('onblur', 'verificarSenhas()');
    });
});

var verificarSenhas = function(){
  var senha_1 = $('#ipt_senha_cad').val();  
  var senha_2 = $('#ipt_senha_2_cad').val();
  if(senha_1 !== '' || senha_2 !== ''){
      if( senha_1 !== senha_2){
          $('.senha').css('border-color', 'red');
      }else{
          $('.senha').css('border-color', 'blue');
      }
  }
};
