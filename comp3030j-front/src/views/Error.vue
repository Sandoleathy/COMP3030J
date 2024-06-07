<template>
  <div class="error-page">
    <h1>{{ t('error.errorTipsTitle') }}</h1>
    <h3>{{ t('error.errorReasonTip') }}</h3>
    <ul>
      <li>{{ t('error.errorReason1') }}</li>
      <li>{{ t('error.errorReason2') }}</li>
      <li>{{ t('error.errorReason3') }}</li>
      <li>{{ t('error.errorReason4') }}</li>
    </ul>
    <p>{{ t('error.errorLinkTip') }}</p>
    <router-link to="/">{{ t('error.errorLink') }}</router-link>
  </div>
</template>
<script setup>
import { useI18n } from 'vue-i18n';
import {useRouter} from "vue-router";

const router = useRouter();

const { t } = useI18n();
</script>
<style scoped>
.error-page {
  text-align: center;
  margin-top: 50px;
}
.error-page h1 {
  color: #d9534f;
}
.error-page ul {
  list-style-type: none;
  padding: 0;
}
.error-page ul li {
  margin: 10px 0;
}
.error-page p {
  margin-top: 20px;
}
</style>