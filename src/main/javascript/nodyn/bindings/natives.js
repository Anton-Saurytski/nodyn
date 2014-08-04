"use strict";

var getSource = io.nodyn.natives.NativesWrap.getSource;

var source = {};

[
  'nodyn',

  'events',
  'util',

  'buffer',
  'nodyn/codec',
  'nodyn/codec/base64',
  'nodyn/codec/hex',
  'nodyn/codec/us_ascii',
  'nodyn/codec/utf8',
  'nodyn/codec/utf16le',

  'tracing',

  'path',
  'module',

  'vm',

  'assert',

  'fs',
  'nodyn/blocking',

  'stream',
  '_stream_readable',
  '_stream_writable',
  '_stream_duplex',
  '_stream_transform',
  '_stream_passthrough',

  'punycode',
  'os',

  'tty',
  'nodyn/streams',

  'repl',
  'readline',
  'console',
  'domain',

  'string_decoder',

  'net',
  'querystring',
  'http',
  '_http_agent',
  '_http_client',
  '_http_server',
  '_http_incoming',
  '_http_outgoing',
  '_http_common',
  'url',
  'dns',
  'dgram',

  'timers',
  '_linklist',
  'freelist',

  'zlib',

  'cluster',
  'child_process',

  'crypto',
  'crypto/cipher_common',
  'crypto/hash',
  'crypto/hmac',
  'crypto/cipher',
  'crypto/decipher',
  'crypto/sign',
  'crypto/verify',
  'crypto/sign_common',

  'crypto/pbkdf2',
  'crypto/random',

  'nodyn/bindings/async_wrap',
  'nodyn/bindings/handle_wrap',
  'nodyn/bindings/stream_wrap',
  'nodyn/bindings/timer_wrap',
  'nodyn/bindings/tcp_wrap',
  'nodyn/bindings/pipe_wrap',
  'nodyn/bindings/signal_wrap',
  'nodyn/bindings/tty_wrap',
  'nodyn/bindings/uv',
  'nodyn/bindings/cares_wrap',
  'nodyn/bindings/v8',
  'nodyn/bindings/zlib',
  'nodyn/bindings/http_parser',

].forEach( function(name) {
  source[name] = getSource(name);
});

source.config = "{}";

module.exports = source;
