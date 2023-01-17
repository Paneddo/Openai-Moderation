# OpenAI Moderation Spigot Plugin
### Spigot chat moderation Plugin based on [OpenAI](https://openai.com/)

### Requirements ⚙️
- OpenAI Account
- OpenAI API key, you can get yours for free [here](https://beta.openai.com/account/api-keys)

Once you got your api key place that into the config.yml file,
witouth a valid api key the plugin won't enable.

### OpenAI API Moderation Endpoint 🛡️
This plugin uses the [moderation endpoint](https://beta.openai.com/docs/guides/moderation/overview)
of the OpenAI API. It blocks every chat message that the model classifies the content as violating OpenAI's [content policy](https://beta.openai.com/docs/usage-policies/content-policy).
In the config.yml file you can choose between two models: `text-moderation-stable` and `text-moderation-latest`

## TO-DO 📒
- [ ] Don't block messages sent by players that have specific permissions. 
- [ ] Block any message that contains certain words before checking it with OpenAI
