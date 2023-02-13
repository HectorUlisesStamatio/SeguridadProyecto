/*=============== MOSTRAR MENU ===============*/
const navMenu = document.getElementById('nav-menu'),
    navToggle = document.getElementById('nav-toggle'),
    navClose = document.getElementById('nav-close')

/*===== MOSTRAR MENU =====*/
if(navToggle){
    navToggle.addEventListener('click', () =>{
        navMenu.classList.add('show-menu')
    })
}

/*===== ESCONDER MENU =====*/
if(navClose){
    navClose.addEventListener('click', () =>{
        navMenu.classList.remove('show-menu')
    })
}

/*=============== QUITAR MENU EN CELULAR ===============*/
const navLink = document.querySelectorAll('.nav__link')

function linkAction(){
    const navMenu = document.getElementById('nav-menu')
    navMenu.classList.remove('show-menu')
}
navLink.forEach(n => n.addEventListener('click', linkAction))

/*=============== SCROLL REVEAL ===============*/
const sr = ScrollReveal({
    distance: '90px',
    duration: 3000,
})

sr.reveal(`.home__data`, {origin: 'top', delay: 400})
sr.reveal(`.home__img`, {origin: 'bottom', delay: 600})
sr.reveal(`.home__footer`, {origin: 'bottom', delay: 800})