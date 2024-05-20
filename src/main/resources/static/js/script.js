const toggleThemeBtn = document.getElementById('theme-change-btn');
const themeText = toggleThemeBtn.querySelector('span');

function setThemeInLocalStorage(theme) {
  localStorage.setItem('theme', theme);
}

function getThemeFromLocalStorage() {
  const savedTheme = localStorage.getItem('theme');
  return savedTheme ? savedTheme : 'light';
}

function setPageTheme(currentTheme) {
  if (currentTheme === 'light') {
    document.querySelector('html').classList.remove('dark');
    document.querySelector('html').classList.add('light');
  } else {
    document.querySelector('html').classList.remove('light');
    document.querySelector('html').classList.add('dark');
  }
  setThemeText(currentTheme);
}

function setThemeText(theme) {
  if (theme === 'light') {
    themeText.textContent = 'Dark';
  } else {
    themeText.textContent = 'Light';
  }
}

function handleThemeChangeBtnClick() {
  const currentTheme = getThemeFromLocalStorage();
  if (currentTheme === 'light') {
    setPageTheme('dark');
    setThemeInLocalStorage('dark');
  } else {
    setPageTheme('light');
    setThemeInLocalStorage('light');
  }
}

const theme = getThemeFromLocalStorage();
setPageTheme(theme);

toggleThemeBtn.addEventListener('click', handleThemeChangeBtnClick);
