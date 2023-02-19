const routes = [
  {
    path: "/",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      {
        path: "",
        name: "home",
        component: () => import("pages/IndexPage.vue"),
      },
      {
        path: "/escolher-produtos",
        name: "escolher-produtos",
        component: () => import("pages/EscolherProdutos.vue"),
      },
      {
        path: "/carrinho",
        name: "carrinho",
        component: () => import("pages/Carrinho.vue"),
      },
    ],
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: "/:catchAll(.*)*",
    component: () => import("pages/ErrorNotFound.vue"),
  },
];

export default routes;
