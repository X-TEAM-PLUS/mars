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
    {
        path: '/member_kt/',
        url: './member_kt.html',
        name: 'member_kt',
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
