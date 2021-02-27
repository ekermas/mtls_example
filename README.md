
```
curl --cacert /mnt/c/workspace/mtls-example/cert/server/identity.crt https://localhost
```

```
curl \
--cacert /mnt/c/workspace/mtls-example/cert/server/identity.crt \
--key /mnt/c/workspace/mtls-example/cert/client_1/identity.key \
--cert /mnt/c/workspace/mtls-example/cert/client_1/identity.crt \
https://localhost
```
