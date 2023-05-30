const tbody = document.querySelector('#tbody');

	  fetch('getAllInfo')
	  .then(resp => resp.json())
	  .then(list => {
		  for (let member of list){
			  const{ id, username, password, nickname, pass, lastUpdateDate } = member
			tbody.innerHTML += `
			<tr>
				<td>${id}</td>
                <td>${username}</td>
                <td>${password}</td>
                <td>${nickname}</td>
                <td>${pass}</td>
                <td>${lastUpdateDate}</td>	  
                </tr>
                `;
			  }
		  });
	 