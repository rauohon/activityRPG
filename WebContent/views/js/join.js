// JavaScript Validation For Registration Page


$(function()

{    

	// id validation
	var idregex = /^[a-zA-Z0-9]+$/;
	$.validator.addMethod("validid", function( value, element ) {
		return this.optional( element ) || idregex.test( value );
	});
	   
	// name validation
	var nameregex =  /^[가-힣a-zA-Z]+$/;
	$.validator.addMethod("validname", function( value, element ) {
		return this.optional( element ) || nameregex.test( value );
	}); 

	// birth validation
	var birthregex = /^([0-9])+$/;
	$.validator.addMethod("validbirth", function( value, element ) {
		return this.optional( element ) || birthregex.test( value );
	});
	
	// age validation
	var ageregex = /^([0-9])+$/;
	$.validator.addMethod("validage", function( value, element ) {
		return this.optional( element ) || ageregex.test( value );
	});
	
	// phone validation
	var phoneregex =  /^[0-9-]+$/;
	$.validator.addMethod("validphone", function( value, element ) {
		return this.optional( element ) || phoneregex.test( value );
	});

	// valid email pattern
	var eregex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	$.validator.addMethod("validemail", function( value, element ) {
		return this.optional( element ) || eregex.test( value );
	});
	
	$("#register-form").validate({
    rules:
    {
    	id: {
   	     required: true,
   	     validid: true,
   	     minlength: 5,
   	     maxlength: 15
   	    },
    	
   	    pwd: {
	     required: true,
	     minlength: 5,
	     maxlength: 15
	    },
	    
	    spwd: {
	     required: true,
	     equalTo: '#password'
	    },	
	    
	    name: {
	     required: true,
	     validname: true,
	     minlength: 2,
   	     maxlength: 5
	    },
	    
	    birth: {
		     required: true,
		     validbirth: true,
		     minlength: 6,
	   	     maxlength: 6
		 },
		 
		 age: {
		     required: true,
		     validage: true,
		     minlength: 1,
	   	     maxlength: 2
		 },
	    
	    phone: {
	     required: true,
	     validphone: true,
	     minlength: 13,
   	     maxlength: 13
	    },
	    
	    email: {
	     required: true,
	     validemail: true
	    },

     },
     
     messages:
     {
    	 id: {
    		 required: "아이디를 입력해주세요.",
    		 validid: "아이디를 제대로 입력해주세요.(대/소문자,숫자만)",
    		 minlength: "아이디 5자리 이상 입력해주세요.",
    		 maxlength: "아이디 15자리를 넘어가지 말아주세요."
    	 },

    	 pwd:{
    		 required: "패스워드를 입력해주세요.",
    		 minlength: "패스워드 5자리 이상 입력해주세요.",
    		 maxlength: "패스워드 15자리를 넘어가지 말아주세요."
    	 },

    	 spwd:{
    		 required: "패스워드를 재입력해주세요.",
    		 equalTo: "패스워드가 일치하지 않습니다."
    	 },
    	     	 
    	 name: {
    		 required: "이름을 입력해주세요.",
    		 validname: "이름을 제대로 입력해주세요.(한글만)",
    		 minlength: "이름을 최소 2글자 입력해주세요.",
    	     maxlength: "이름을 5글자 넘기지 말아주세요."
    	 },
    	 
    	 birth: {
    		 required: "생년월일을 입력해주세요.",
    		 validbirth: "생년월일을 제대로 입력해주세요.(ex.901010)",
    		 minlength: "숫자 6자리를 입력해주세요.",
    	     maxlength: "숫자 6자리를 입력해주세요."
    	 },
    	
    	 age: {
    		 required: "나이를 입력해주세요.",
    		 validage: "나이를 제대로 입력해주세요.(숫자)",
    		 minlength: "최소 1자리를 입력해주세요.",
    	     maxlength: "최대 2자리를 입력해주세요."
    	 },
    	 
    	 email: {
    		 required: "메일을 입력해주세요.",
    		 validemail: "메일을 제대로 입력해주세요."
    	 },

    	 phone: {
    		 required: "핸드폰 번호를 입력해주세요.",
    		 validphone: "핸드폰 번호를 제대로 입력해주세요.",
    		 minlength: "하이픈(-)과 함께 13자리 입력해주세요.",
    	     maxlength: "하이픈(-)과 함께 13자리 입력해주세요."
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