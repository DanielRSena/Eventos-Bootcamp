import fastify from "fastify"

import {
    serializerCompiler,validatorCompiler, type ZodTypeProvider
} from "fastify-type-provider-zod"
import { fastifyCors } from "@fastify/cors"
import { env } from './env.ts'

const app = fastify().withTypeProvider<ZodTypeProvider>()

app.register(fastifyCors, {
    origin: 'http://localhost:5173', // Permite todas as origens
})

app.setSerializerCompiler(serializerCompiler)
app.setValidatorCompiler(validatorCompiler)

app.get("/health", async () => {
    return {
        message: "Bem vindo ao servidor, tudo bem por aqui! Essa mensagem foi gerada no arquivo server.ts, localizado na pasta src."
    }
})

app.listen({port: env.PORT}).then(() => {
    console.log(`HTTP Server rodando no endereço http://localhost:${process.env.PORT || 3333}!`)
})