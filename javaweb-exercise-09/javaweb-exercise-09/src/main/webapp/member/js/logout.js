document.querySelector('#btLogout').addEventListener('click', () => {
	if (confirm('是否登出')){			
	fetch('logout')
	sessionStorage.removeItem('nickname');
	location.replace('login.html');
}
});
