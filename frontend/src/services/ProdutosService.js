import useApi from "src/composables/UseApi";

export default function produtosService() {
  const { list, remove } = useApi("api/produtos");

  return {
    list,
    remove,
  };
}
