const { defineConfig } = require('@vue/cli-service')

module.exports = {
  outputDir: '../backend/src/main/resources/static',
  devServer: {
    port: 9999,
    proxy: {
      '^/api': {
        target: 'http://localhost:8080'
      }
    }
  }
}