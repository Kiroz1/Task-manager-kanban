console.log("board.js cargado");

let dragged = null;

document.querySelectorAll(".task-card").forEach(card => {

card.addEventListener("dragstart", function(){

dragged = this;

});

});


document.querySelectorAll(".task-column").forEach(column => {

column.addEventListener("dragover", function(e){

e.preventDefault();

});


column.addEventListener("dragenter", function(){

this.classList.add("drag-over");

});


column.addEventListener("dragleave", function(){

this.classList.remove("drag-over");

});


column.addEventListener("drop", function(e){

e.preventDefault();

this.classList.remove("drag-over");

if(!dragged) return;

let id = dragged.dataset.id;

let estado = this.dataset.estado;

fetch(`/board/mover/${id}/${estado}`,{

method:"PUT"

})
.then(res=>{

if(res.ok){

location.reload();

}

});

});

});


function eliminar(id){

fetch(`/board/eliminar/${id}`,{

method:"DELETE"

})
.then(()=>location.reload());

}