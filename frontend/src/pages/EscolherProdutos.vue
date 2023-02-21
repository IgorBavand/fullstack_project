<template>
  <div class="q-pa-md row items-start q-gutter-md">
    <div v-for="produto in produtos" :key="produto.id">
      <q-card class="my-card">
        <q-img src="https://cdn.quasar.dev/img/chicken-salad.jpg" />

        <q-card-section>
          <div class="row no-wrap items-center">
            <div class="col text-h6 ellipsis">{{ produto.nome }}</div>
          </div>

          <div class="col text-h6 ellipsis">{{ produto.preco }}</div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          <div class="text-caption text-grey">
            {{ produto.descricao }}
          </div>
        </q-card-section>

        <q-separator />

        <q-card-actions>
          <q-btn flat round icon="shopping_cart" />
          <q-btn flat color="primary"> Adicionar ao carrinho </q-btn>
        </q-card-actions>
      </q-card>
    </div>
  </div>
</template>

<script>
import { defineComponent, onMounted, ref } from "vue";
import produtosService from "../services/ProdutosService";

export default defineComponent({
  // eslint-disable-next-line vue/multi-word-component-names
  name: "EscolherProdutos",

  setup() {
    const produtos = ref([]);
    const { list } = produtosService();

    const getProdutos = async () => {
      try {
        const response = await list();
        produtos.value = response.content;
      } catch (error) {
        console.log(error);
      }
    };

    onMounted(() => {
      getProdutos();
    });

    return {
      produtos,
    };
  },
});
</script>

<style scoped>
.my-card {
  width: 100%;
  max-width: 300px;
}
</style>
