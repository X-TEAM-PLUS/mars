var routes = [
  // Index page
  {
    path: '/',
    url: './index.html',
    name: 'home',
  },
    // 公益会员
    {
        path: '/member_gy/',
        url: './pages/member_gy.html',
        name: 'member_gy',
    },
    //社工
    {
        path: '/member_sg/',
        url: './pages/member_sg.html',
        name: 'member_sg',
    },
    //理事
    {
        path: '/member_ls/',
        url: './pages/member_ls.html',
        name: 'member_ls',
    },

    //常务理事
    {
        path: '/member_cwls/',
            url: './pages/member_cwls.html',
        name: 'member_cwls',
    },

    //入口
    {
        path: '/member_rk/',
        url: './pages/member_rk.html',
        name: 'member_rk',
    },
    //开通
    {
        path: '/member_kt/',
        url: './pages/member_kt.html',
        name: 'member_kt',
    },
    //支付方式
    {
        path: '/pay_way/',
        url: './pages/pay_way.html',
        name: 'pay_way',
    },
    //健康检查
    {
        path: '/heart_check/',
        url: './pages/heart_check.html',
        name: 'heart_check',
    },
    //好医生
    {
        path: '/good_doctor/',
        url: './pages/good_doctor.html',
        name: 'good_doctor',
    },
  // Default route (404 page). MUST BE THE LAST
  {
    path: '(.*)',
    url: './pages/404.html',
  },
];
