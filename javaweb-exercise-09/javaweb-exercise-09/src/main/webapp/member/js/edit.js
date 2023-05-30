const username = document.querySelector('#username');
const password = document.querySelector('#password');
const cPassword = document.querySelector('#cPassword');
const nickname = document.querySelector('#nickname');
const msg = document.querySelector('#msg');  
const divNickname = document.querySelector('#divNickname'); 

divNickname.textContent = sessionStorage.getItem('nickname');

	  fetch('getInfo')
	  .then(resp => resp.json())
	  .then(member => {
	   username.value = member.username;
	   nickname.value = member.nickname;
	  });



	 document.querySelector('#btSave').addEventListener('click', e =>{
	  if(password.value !== cPassword.value){
	   return;  
	  }
	  
	  fetch('edit', {
	   method: 'POST',
	   headers: {
	    'Content-Type': 'application/json'
	   },
	   body: JSON.stringify({
	    password: password.value,
	    nickname: nickname.value
	   })
	  })
	  .then(resp => resp.json())
	  .then(body => {
		  if(body.successful){
			  const { member } = body;
			  const { username, nickname } = member;
			  msg.textContent = `${username}`, `${nickname}`
		  }else{
			 msg.textContent = body.message
		  }
	   alert(`${body.successful}, ${body.message}`);
	  });
	 

});