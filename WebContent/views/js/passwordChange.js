$(function(){

	
	$("#changePwd").validate({
	    rules:
	    {
	    	changePwd: {
		     required: true,
		     minlength: 5,
		     maxlength: 15
		    },
		    
		    changeSpwd: {
		     required: true,
		     equalTo: '#pwd'
		    }
	     },
	     
	     messages:
	     {
	    	 changePwd:{
	    		 required: "패스워드를 입력해주세요.",
	    		 minlength: "패스워드 5자리 이상 입력해주세요.",
	    		 maxlength: "패스워드 15자리를 넘어가지 말아주세요."
	    	 },

	    	 changeSpwd:{
	    		 required: "패스워드를 재입력해주세요.",
	    		 equalTo: "패스워드가 일치하지 않습니다."
	    	 }
	     },

	     errorPlacement : function(error, element) {
	    	 $(element).closest('.form-group').find('.help-block').html(error.html());
	     },

	     highlight : function(element) {
	    	 $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
	     },

	     unhighlight: function(element, errorClass, validClass) {
	    	 $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
	    	 $(element).closest('.form-group').find('.help-block').html('');
	     },

	     submitHandler: function(form) {
	    	 
	 		form.submit();
	 		alert('ok');
	     }
	});
});