import { api } from "src/boot/axios";

export default function useApi(url) {
  const list = async () => {
    try {
      const response = await api.get(url);
      return response.data;
    } catch (error) {
      throw new Error(error);
    }
  };

  const remove = async (id) => {
    try {
      await api.delete(`${url}/${id}`);
    } catch (error) {
      throw new Error(error);
    }
  };

  return {
    list,
    remove,
  };
}
