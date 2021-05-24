import vueInstall from "./vue-install.vue"
const vueFirstInstall = {
  install(Vue, option) {
    Vue.component(vueInstall.name, vueInstall)
  }
}

if (typeof window !== 'undefined' && window.Vue) {
  window.Vue.use(vueFirstInstall);
}
export default vueFirstInstall
