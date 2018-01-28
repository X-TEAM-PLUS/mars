var routes = [
  // Index page
  {
    path: '/',
    url: './index.html',
    name: 'home',
  },
    {
        path: '/member_rk/',
        url: './member_rk.html',
        name: 'member_rk',
    },
  // About page
  {
    path: '/visitor/',
    url: './index.html',
    name: 'visitor',
  },
  // Default route (404 page). MUST BE THE LAST
  {
    path: '(.*)',
    url: './pages/404.html',
  },
];
