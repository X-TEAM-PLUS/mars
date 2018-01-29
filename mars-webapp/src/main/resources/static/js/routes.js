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
    path: '/member_gy/',
    url: './member_gy.html',
    name: 'member_gy',
  },
    {
        path: '/pay_way/',
        url: './pay_way.html',
        name: 'pay_way',
    },
  // Default route (404 page). MUST BE THE LAST
  {
    path: '(.*)',
    url: './pages/404.html',
  },
];
