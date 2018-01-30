var routes = [
  // Index page
  {
    path: '/',
    url: './index.html',
    name: 'home',
  },
    {
        path: '/member_rk/',
        url: './pages/member_rk.html',
        name: 'member_rk',
    },
    {
        path: '/member_kt/',
        url: './pages/member_kt.html',
        name: 'member_kt',
    },
  // About page
  {
    path: '/member_gy/',
    url: './pages/member_gy.html',
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
