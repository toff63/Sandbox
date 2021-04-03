const path = require('path');
const webpack = require('webpack');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const { WebpackManifestPlugin } = require('webpack-manifest-plugin');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');
const devMode = process.env.NODE_ENV !== 'production';

const plugins = [
  new CleanWebpackPlugin(),
  new MiniCssExtractPlugin({
    filename: devMode ? '[name].css' : '[name].[contenthash].css',
    chunkFilename: devMode ? '[id].css' : '[id].[contenthash].css',
  }),
  new WebpackManifestPlugin({
    fileName: '../_data/manifest.yml',
    publicPath: './dist/'
  }),
]

if (devMode) {
  // only enable hot in development
  plugins.push(new webpack.HotModuleReplacementPlugin())
}

module.exports = {
  entry: {
    docs: path.resolve(__dirname, './assets/index.css'),
  },
  output: {
    path: path.resolve(__dirname, './dist/'),
    filename: devMode ? '[name].js' : '[name].[contenthash].js',
    chunkFilename: devMode ? '[id].js' : '[id].[contenthash].js',
  },
  module: {
    rules: [
      {
        test: /\.css$/,
        use: [
          MiniCssExtractPlugin.loader,
          'css-loader',
          'postcss-loader',
        ]
      }
    ]
  },
  plugins: plugins,
};

