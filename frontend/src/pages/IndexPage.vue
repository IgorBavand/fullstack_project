<template>
  <q-page padding>
    <q-table title="Treats" :rows="produtos" :columns="columns" row-key="id">
      <template v-slot:body-cell-image="props">
        <q-td :props="props">
          <q-img
            src="../../../../../../../tmp/caixa/images/dondiego.jpg"
            loading="lazy"
            spinner-color="white"
            height="140px"
            style="max-width: 150px"
          />
        </q-td>
      </template>

      <template v-slot:body-cell-actions="props">
        <q-td :props="props">
          <q-btn
            icon="delete"
            color="negative"
            dense
            @click="handlerRemove(props.row.id)"
          />
        </q-td>
      </template>
    </q-table>
  </q-page>
</template>

<script>
import { defineComponent, ref, onMounted } from "vue";
import produtosService from "../services/ProdutosService";
import { useQuasar } from "quasar";

export default defineComponent({
  name: "IndexPage",

  setup() {
    const produtos = ref([]);
    const { list, remove } = produtosService();

    const $q = useQuasar();

    const columns = [
      { name: "image", label: "Imagem", field: "image", align: "left" },

      {
        name: "nome",
        required: true,
        label: "Nome",
        align: "left",
        field: "nome",
        sortable: true,
      },
      {
        name: "descricao",
        label: "Descrição",
        field: "descricao",
        sortable: true,
      },
      {
        name: "preco",
        label: "Preço",
        field: "preco",
        sortable: true,
        align: "left",
      },
      {
        name: "estoque",
        label: "Qtd. Estoque",
        field: "estoque",
        sortable: true,
        align: "left",
      },
      { name: "actions", label: "Ações", field: "actions", align: "right" },
    ];

    const getProdutos = async () => {
      try {
        const response = await list();
        produtos.value = response.content;
      } catch (error) {
        console.log(error);
      }
    };

    const handlerRemove = async (id) => {
      try {
        $q.dialog({
          title: "Remover",
          message: "Tem certeza que quer remover o produto?",
          cancel: true,
          persistent: true,
        }).onOk(async () => {
          await remove(id);
          $q.notify({
            message: "Apagado com sucesso!",
            icon: "check",
            color: "positive",
          });
          getProdutos();
        });
      } catch (error) {
        console.log(error);
      }
    };

    onMounted(() => {
      getProdutos();
    });

    return {
      columns,
      produtos,
      handlerRemove,
    };
  },
});
</script>
