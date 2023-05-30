const username = document.querySelector('#username');
const password = document.querySelector('#password');

	 document.querySelector('button').addEventListener('click', e =>{
	  if(!username.value || !password.value){
	   return;  
	  }
	 
	  fetch('login', {
	   method: 'POST',
	   headers: {
	    'Content-Type': 'application/json'
	   },
	   body: JSON.stringify({
	    username: username.value,
	    password: password.value
	   })
	  })
	  .then(resp => resp.json())
	  .then(body => {
	   if(body.successful){
		   sessionStorage.setItem('nickname', body.nickname);
		   location = 'edit.html';
	   }else{
		   msg.textContent = body.message;
	   }
	  });
	 });