// index.js
import { createI18n } from 'vue-i18n';
import Cookies from 'js-cookie';
import elementEnLocale from 'element-plus/es/locale/lang/en';
import elementZhLocale from 'element-plus/es/locale/lang/zh-cn';
import enLocale from './en';
import zhLocale from './zh';

const messages = {
    en: {
        ...enLocale,
        ...elementEnLocale
    },
    zh: {
        ...zhLocale,
        ...elementZhLocale
    }
};

const i18n = createI18n({
    legacy: false, // you must set `legacy: false` for Vue 3
    locale: Cookies.get('language') || 'en', // set locale
    messages, // set locale messages
});

export default i18n;
