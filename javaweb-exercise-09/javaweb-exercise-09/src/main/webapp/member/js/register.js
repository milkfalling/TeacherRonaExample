const username = document.querySelector('#username');
	 const password = document.querySelector('#password');
	 const cPassword = document.querySelector('#cPassword');
	 const nickname = document.querySelector('#nickname');
	 document.querySelector('button').addEventListener('click', e =>{
	  if(password.value !== cPassword.value){
	   return;  
	  }
	 
	  fetch('register', {
	   method: 'POST',
	   headers: {
	    'Content-Type': 'application/json'
	   },
	   body: JSON.stringify({
	    username: username.value,
	    password: password.value,
	    nickname: nickname.value
	   })
	  })
	  .then(resp => resp.json())
	  .then(body => {
	   alert(`${body.successful}, ${body.message}`);
	  });
	 });