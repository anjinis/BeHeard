module Api
	module V1
		class LikesController < ApplicationController
			def create
				@post = Post.find(params[:post_id])
				@like = Like.new(like_params)
				render json: @like
			end
			def destroy
				@post = Post.find(params[:post_id])
				@like = Like.find(params[:id])
				@like.destroy
				render json: @like
			end
			private
			def like_params
				params.require(:like).permit(:post_id)
			end
		end
	end
end
