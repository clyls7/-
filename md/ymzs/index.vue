<template>
  <div>
    <iframe :src="externalUrl" width="100%" height="100%" frameborder="0"></iframe>
  </div>
</template>

<script>
export default {
  props: {
    externalUrl: {
      type: String,
      required: true
    }
  }
}
</script>
