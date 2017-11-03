$(function(){

	$("#pwdChange").validate({
		rules:
		{
			/*pw: {
			required: true,
			validid: true,
			minlength: 5,
			maxlength: 15
		},
			 */
			changePwd: {
				required: true,
				minlength: 5,
				maxlength: 15
			},

			changeSpwd: {
				required: true,
				equalTo: '#pwd'
			},
		},

		messages:
		{
			/*pw: {
			required: "아이디를 입력해주세요.",
			validid: "아이디를 제대로 입력해주세요.(대/소문자,숫자만)",
			minlength: "아이디 5자리 이상 입력해주세요.",
			maxlength: "아이디 15자리를 넘어가지 말아주세요."
		},
			 */
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