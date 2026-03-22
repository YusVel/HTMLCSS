
var table = document.getElementById('table');
let tr = document.createElement('tr');
let thName = document.createElement('th');
let thead = document.createElement('thead');
thName.innerHTML = "ФИО";
tr.appendChild(thName);
for(i = 1;i<=31;i++)
{
    let th = document.createElement('th');
    th.innerHTML = i;
    tr.appendChild(th);
}
thead.appendChild(tr);
table.appendChild(thead);
let tbody = document.createElement('tbody');

for (let i = 0; i < 10; i++) {
	let tr = document.createElement('tr');
	let th = document.createElement('th');
    th.innerHTML = '-----';
    tr.appendChild(th);
	for (let j = 1; j <= 31; j++) {
		let td = document.createElement('td');
        td.innerHTML = "У";
		tr.appendChild(td);
	}
	
	tbody.appendChild(tr);
}
table.appendChild(tbody);
