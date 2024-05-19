/** @type {import('tailwindcss').Config} */

import defaultTheme from 'tailwindcss/defaultTheme';

export default {
  content: ['./src/main/resources/**/*.{html,js}'],
  theme: {
    extend: {},
    fontFamily: {
      'roboto': ['Roboto', ...defaultTheme.fontFamily.sans],
    },
  },
  plugins: [],
  darkMode: 'selector',
};
